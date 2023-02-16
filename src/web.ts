import { WebPlugin } from '@capacitor/core';

import type { VNPaySDKIntegratedPlugin } from './definitions';

export class VNPaySDKIntegratedWeb
  extends WebPlugin
  implements VNPaySDKIntegratedPlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
