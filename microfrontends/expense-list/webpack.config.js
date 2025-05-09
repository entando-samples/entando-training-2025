module.exports = {
    entry: "/src/main.ts",
    output: {
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
  