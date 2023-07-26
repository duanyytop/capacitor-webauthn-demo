import {
  PublicKeyCredentialCreationOptionsJSON,
  PublicKeyCredentialRequestOptionsJSON,
  RegistrationResponseJSON,
  AuthenticationResponseJSON,
} from '@simplewebauthn/typescript-types';

export interface WebAuthnPlugin {
  isWebAuthnAvailable(): Promise<{ value: boolean }>;
  create(
    publicKeyCredentialCreationOptionsJSON: PublicKeyCredentialCreationOptionsJSON,
  ): Promise<RegistrationResponseJSON>;
  get(
    requestOptionsJSON: PublicKeyCredentialRequestOptionsJSON,
    useBrowserAutofill?: boolean,
  ): Promise<AuthenticationResponseJSON>;
}
