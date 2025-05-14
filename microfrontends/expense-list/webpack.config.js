module.exports = {
    entry: "/src/main.ts",
    output: {
/**
       * the `library` property is used to specify a name for the exported library. 
       * When you set this property, Webpack treats the output as a library 
       * that can be consumed by other applications or scripts. 
       * In this case, the library is named "angular-widget". 
       * This means that when the bundle is included in a project 
       * (e.g., via a <script> tag or as a module), 
       * it will expose its exports under the global variable or module name angular-widget.
       */
      library: "expense-list",
      /**
       * UMD (Universal Module Definition) is a JavaScript module format designed to work in any environment,
       * including CommonJS (Node.js), AMD (RequireJS), and browser globals.
       * It ensures maximum compatibility for libraries and reusable components.
       */
      libraryTarget: "umd",
    },
    /**
     * zone-polyfill fragment will inject the polyfill
     * at runtime must include the frament inside the template that use the Angular MFE
     * */
    externals: {
      zone: "zone",
    },
    /**
     * runtime chunk must be disable for prevent runtime collision with other MFEs in the same page
     */
    optimization: {
      runtimeChunk: false,
      minimize: true
    },
  };
