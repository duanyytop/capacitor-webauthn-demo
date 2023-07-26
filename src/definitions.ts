import {
  PublicKeyCredentialCreationOptionsJSON,
  PublicKeyCredentialRequestOptionsJSON,
  RegistrationResponseJSON,
  AuthenticationResponseJSON,
} from './type';

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
