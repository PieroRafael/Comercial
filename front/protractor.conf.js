const { SpecReporter } = require('jasmine-spec-reporter');

exports.config = {
  allScriptsTimeout: 11000,
  specs: [
    './e2e/**/*.e2e-spec.ts'
  ],
  capabilities: {
    'browserName': 'chrome',
    'chromeOptions': {
      'args': ['show-fps-counter=true', '--no-sandbox']
    }
  },
  directConnect: true,
  baseUrl: 'http://localhost:4200/',
  framework: 'jasmine',
  jasmineNodeOpts: {
    showColors: true,
    defaultTimeoutInterval: 30000,
    print: function () { }
  },
  onPrepare: async () => {
    require('ts-node').register({
      project: 'e2e/tsconfig.e2e.json'
    });

    jasmine.getEnv().addReporter(new SpecReporter({ acspec: { displayStacktrace: true } }));
  },
  plugins: [{
    package: 'protractor-console-plugin',
    failOnWarning: false,
    failOnError: true,
    logWarnings: true,
    exclude: []
  }]
};
