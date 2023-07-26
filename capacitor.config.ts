import { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'dev.joyid.app',
  appName: 'demo',
  webDir: 'dist',
  server: {
    androidScheme: 'https',
  },
};

export default config;
