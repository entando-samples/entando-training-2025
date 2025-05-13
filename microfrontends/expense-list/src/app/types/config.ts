export interface EntandoConfig {
  contextParams: {
    page_code: string;
    info_currentLang: string;
    systemParam_applicationBaseURL: string;
  };
  systemParams: {
    api: {
        "expense-api": {
            url: string;
        }
    }
  },
  params: {
    role: 'admin' | 'user';
  }
}
