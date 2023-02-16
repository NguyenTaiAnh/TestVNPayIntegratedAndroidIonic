export interface VNPaySDKIntegratedPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
