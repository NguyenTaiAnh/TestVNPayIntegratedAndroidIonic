import { registerPlugin } from '@capacitor/core';

import type { VNPaySDKIntegratedPlugin } from './definitions';

const VNPaySDKIntegrated = registerPlugin<VNPaySDKIntegratedPlugin>(
  'VNPaySDKIntegrated',
  {
    web: () => import('./web').then(m => new m.VNPaySDKIntegratedWeb()),
  },
);

export * from './definitions';
export { VNPaySDKIntegrated };
