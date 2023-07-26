import { WebPlugin } from '@capacitor/core';
import {
  browserSupportsWebAuthn,
  startAuthentication,
  startRegistration,
} from '@simplewebauthn/browser';
import {
  AuthenticationResponseJSON,
  PublicKeyCredentialCreationOptionsJSON,
  PublicKeyCredentialRequestOptionsJSON,
  RegistrationResponseJSON,
} from '@simplewebauthn/typescript-types';

import type { WebAuthnPlugin } from './definitions';

export class WebAuthnWeb extends WebPlugin implements WebAuthnPlugin {
  async create(
    publicKeyCredentialCreationOptionsJSON: PublicKeyCredentialCreationOptionsJSON,
  ): Promise<RegistrationResponseJSON> {
    return startRegistration(publicKeyCredentialCreationOptionsJSON);
  }

  async get(
    requestOptionsJSON: PublicKeyCredentialRequestOptionsJSON,
    useBrowserAutofill?: boolean,
  ): Promise<AuthenticationResponseJSON> {
    return startAuthentication(requestOptionsJSON, useBrowserAutofill);
  }

  isWebAuthnAvailable(): Promise<{ value: boolean }> {
    const val = browserSupportsWebAuthn();
    return Promise.resolve({ value: val });
  }
}
