package com.bbd.mvc.modules.api;


import java.lang.module.ModuleReader;
import java.lang.module.ModuleReference;
import java.lang.module.ResolvedModule;
import java.net.URI;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Java9Scanner {

    private static CallerResolver CALLER_RESOLVER;

    static {
        try {
            // This can fail if the current SecurityManager does not allow
            // RuntimePermission ("createSecurityManager"):
            CALLER_RESOLVER = new CallerResolver();
        } catch (final SecurityException e) {
        }
    }

    private static final class CallerResolver extends SecurityManager {
        /** Get classes in the call stack. */
        @Override
        protected Class<?>[] getClassContext() {
            return super.getClassContext();
        }
    }

    /** Recursively find the topological sort order of ancestral layers. */
    private static void findLayerOrder(ModuleLayer layer,
                                       Set<ModuleLayer> visited, Deque<ModuleLayer> layersOut) {
        if (visited.add(layer)) {
            List<ModuleLayer> parents = layer.parents();
            for (int i = 0; i < parents.size(); i++) {
                findLayerOrder(parents.get(i), visited, layersOut);
            }
            layersOut.push(layer);
        }
    }

    /** Get ModuleReferences from a Class reference. */
    private static List<Entry<ModuleReference, ModuleLayer>> findModuleRefs(
            Class<?>[] callStack) {
        Deque<ModuleLayer> layerOrder = new ArrayDeque<>();
        Set<ModuleLayer> visited = new HashSet<>();
        for (int i = 0; i < callStack.length; i++) {
            ModuleLayer layer = callStack[i].getModule().getLayer();
            if(layer !=null) {
                findLayerOrder(layer, visited, layerOrder);
            }
        }
        Set<ModuleReference> addedModules = new HashSet<>();
        List<Entry<ModuleReference, ModuleLayer>> moduleRefs = new ArrayList<>();
        for (ModuleLayer layer : layerOrder) {
            Set<ResolvedModule> modulesInLayerSet = layer.configuration()
                    .modules();
            final List<Entry<ModuleReference, ModuleLayer>> modulesInLayer
                    = new ArrayList<>();
            for (ResolvedModule module : modulesInLayerSet) {
                modulesInLayer.add(new SimpleEntry<>(module.reference(), layer));
            }
            // Sort modules in layer by name for consistency
            Collections.sort(modulesInLayer,
                    (e1, e2) -> e1.getKey().descriptor().name()
                            .compareTo(e2.getKey().descriptor().name()));
            // To be safe, dedup ModuleReferences, in case a module occurs in multiple
            // layers and reuses its ModuleReference (no idea if this can happen)
            for (Entry<ModuleReference, ModuleLayer> m : modulesInLayer) {
                if (addedModules.add(m.getKey())) {
                    moduleRefs.add(m);
                }
            }
        }
        return moduleRefs;
    }

    /**
     * Return true if the given module name is a system module. There can be
     * system modules in layers above the boot layer.
     */
    private static boolean isSystemModule(final String moduleName) {
        return moduleName.startsWith("java.") || moduleName.startsWith("javax.")
                || moduleName.startsWith("javafx.")
                || moduleName.startsWith("jdk.")
                || moduleName.startsWith("oracle.");
    }

    public static void scan() throws Exception {
        // Get ModuleReferences for modules of all classes in call stack,
        List<Entry<ModuleReference, ModuleLayer>> systemModuleRefs = new ArrayList<>();
        List<Entry<ModuleReference, ModuleLayer>> nonSystemModuleRefs = new ArrayList<>();
        if (CALLER_RESOLVER == null) {
            throw new RuntimeException("Current SecurityManager does not grant "
                    + "RuntimePermission(\"createSecurityManager\")");
        } else {
            final Class<?>[] callStack = CALLER_RESOLVER.getClassContext();
            if (callStack == null) {
                throw new RuntimeException(CallerResolver.class.getSimpleName()
                        + "#getClassContext() returned null");
            } else {
                List<Entry<ModuleReference, ModuleLayer>> moduleRefs = findModuleRefs(
                        callStack);
                // Split module refs into system and non-system modules
                // based on module name
                for (Entry<ModuleReference, ModuleLayer> m : moduleRefs) {
                    String moduleName = m.getKey().descriptor().name();
                    (isSystemModule(moduleName) ? systemModuleRefs
                            : nonSystemModuleRefs).add(m);
                }
            }
        }

        // List system modules
        System.out.println("\nSYSTEM MODULES:\n");
        for (Entry<ModuleReference, ModuleLayer> e : systemModuleRefs) {
            ModuleReference ref = e.getKey();
            System.out.println("  " + ref.descriptor().name());
        }

        // Show info for non-system modules
        System.out.println("\nNON-SYSTEM MODULES:");
        for (Entry<ModuleReference, ModuleLayer> e : nonSystemModuleRefs) {
            ModuleReference ref = e.getKey();
            ModuleLayer layer = e.getValue();
            System.out.println("\n  " + ref.descriptor().name());
            System.out.println(
                    "    Version: " + ref.descriptor().toNameAndVersion());
            System.out.println("    Packages: " + ref.descriptor().packages());
            System.out.println("    ClassLoader: "
                    + layer.findLoader(ref.descriptor().name()));
            Optional<URI> location = ref.location();
            if (location.isPresent()) {
                System.out.println("    Location: " + location.get());
            }
            try (ModuleReader moduleReader = ref.open()) {
                Stream<String> stream = moduleReader.list();
                stream.forEach(s -> System.out.println("      File: " + s));
            }
        }
    }
}