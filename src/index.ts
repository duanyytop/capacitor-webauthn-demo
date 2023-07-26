import { registerPlugin } from '@capacitor/core';

import type { WebAuthnPlugin } from './definitions';

const WebAuthn = registerPlugin<WebAuthnPlugin>('WebAuthn');

export * from './definitions';
export { WebAuthn };
