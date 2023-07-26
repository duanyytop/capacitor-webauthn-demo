# @joyid/capacitor-native-passkey

The capacitor plugins of native passkey for Android and iOS

## Install

```bash
npm install @joyid/capacitor-native-passkey
npx cap sync
```

## API

<docgen-index>

* [`isWebAuthnAvailable()`](#iswebauthnavailable)
* [`create(...)`](#create)
* [`get(...)`](#get)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### isWebAuthnAvailable()

```typescript
isWebAuthnAvailable() => Promise<{ value: boolean; }>
```

**Returns:** <code>Promise&lt;{ value: boolean; }&gt;</code>

--------------------


### create(...)

```typescript
create(publicKeyCredentialCreationOptionsJSON: PublicKeyCredentialCreationOptionsJSON) => Promise<RegistrationResponseJSON>
```

| Param                                        | Type                                                                                                      |
| -------------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| **`publicKeyCredentialCreationOptionsJSON`** | <code><a href="#publickeycredentialcreationoptionsjson">PublicKeyCredentialCreationOptionsJSON</a></code> |

**Returns:** <code>Promise&lt;<a href="#registrationresponsejson">RegistrationResponseJSON</a>&gt;</code>

--------------------


### get(...)

```typescript
get(requestOptionsJSON: PublicKeyCredentialRequestOptionsJSON, useBrowserAutofill?: boolean | undefined) => Promise<AuthenticationResponseJSON>
```

| Param                    | Type                                                                                                    |
| ------------------------ | ------------------------------------------------------------------------------------------------------- |
| **`requestOptionsJSON`** | <code><a href="#publickeycredentialrequestoptionsjson">PublicKeyCredentialRequestOptionsJSON</a></code> |
| **`useBrowserAutofill`** | <code>boolean</code>                                                                                    |

**Returns:** <code>Promise&lt;<a href="#authenticationresponsejson">AuthenticationResponseJSON</a>&gt;</code>

--------------------


### Interfaces


#### RegistrationResponseJSON

A slightly-modified RegistrationCredential to simplify working with ArrayBuffers that
are Base64URL-encoded in the browser so that they can be sent as JSON to the server.

https://w3c.github.io/webauthn/#dictdef-registrationresponsejson

| Prop                          | Type                                                                                                  |
| ----------------------------- | ----------------------------------------------------------------------------------------------------- |
| **`id`**                      | <code><a href="#base64urlstring">Base64URLString</a></code>                                           |
| **`rawId`**                   | <code><a href="#base64urlstring">Base64URLString</a></code>                                           |
| **`response`**                | <code><a href="#authenticatorattestationresponsejson">AuthenticatorAttestationResponseJSON</a></code> |
| **`authenticatorAttachment`** | <code>AuthenticatorAttachment</code>                                                                  |
| **`clientExtensionResults`**  | <code>AuthenticationExtensionsClientOutputs</code>                                                    |
| **`type`**                    | <code>PublicKeyCredentialType</code>                                                                  |


#### AuthenticatorAttestationResponseJSON

A slightly-modified AuthenticatorAttestationResponse to simplify working with ArrayBuffers that
are Base64URL-encoded in the browser so that they can be sent as JSON to the server.

| Prop                    | Type                                                        |
| ----------------------- | ----------------------------------------------------------- |
| **`clientDataJSON`**    | <code><a href="#base64urlstring">Base64URLString</a></code> |
| **`attestationObject`** | <code><a href="#base64urlstring">Base64URLString</a></code> |


#### PublicKeyCredentialCreationOptionsJSON

A variant of PublicKeyCredentialCreationOptions suitable for JSON transmission to the browser to
(eventually) get passed into navigator.credentials.create(...) in the browser.

| Prop                     | Type                                                                                            |
| ------------------------ | ----------------------------------------------------------------------------------------------- |
| **`user`**               | <code><a href="#publickeycredentialuserentityjson">PublicKeyCredentialUserEntityJSON</a></code> |
| **`challenge`**          | <code><a href="#base64urlstring">Base64URLString</a></code>                                     |
| **`excludeCredentials`** | <code>PublicKeyCredentialDescriptorJSON[]</code>                                                |
| **`extensions`**         | <code>AuthenticationExtensionsClientInputs</code>                                               |


#### PublicKeyCredentialUserEntityJSON

| Prop     | Type                |
| -------- | ------------------- |
| **`id`** | <code>string</code> |


#### PublicKeyCredentialDescriptorJSON

| Prop             | Type                                                        |
| ---------------- | ----------------------------------------------------------- |
| **`id`**         | <code><a href="#base64urlstring">Base64URLString</a></code> |
| **`transports`** | <code>AuthenticatorTransportFuture[]</code>                 |


#### AuthenticationResponseJSON

A slightly-modified AuthenticationCredential to simplify working with ArrayBuffers that
are Base64URL-encoded in the browser so that they can be sent as JSON to the server.

https://w3c.github.io/webauthn/#dictdef-authenticationresponsejson

| Prop                          | Type                                                                                              |
| ----------------------------- | ------------------------------------------------------------------------------------------------- |
| **`id`**                      | <code><a href="#base64urlstring">Base64URLString</a></code>                                       |
| **`rawId`**                   | <code><a href="#base64urlstring">Base64URLString</a></code>                                       |
| **`response`**                | <code><a href="#authenticatorassertionresponsejson">AuthenticatorAssertionResponseJSON</a></code> |
| **`authenticatorAttachment`** | <code>AuthenticatorAttachment</code>                                                              |
| **`clientExtensionResults`**  | <code>AuthenticationExtensionsClientOutputs</code>                                                |
| **`type`**                    | <code>PublicKeyCredentialType</code>                                                              |


#### AuthenticatorAssertionResponseJSON

A slightly-modified AuthenticatorAssertionResponse to simplify working with ArrayBuffers that
are Base64URL-encoded in the browser so that they can be sent as JSON to the server.

| Prop                    | Type                                                        |
| ----------------------- | ----------------------------------------------------------- |
| **`authenticatorData`** | <code><a href="#base64urlstring">Base64URLString</a></code> |
| **`clientDataJSON`**    | <code><a href="#base64urlstring">Base64URLString</a></code> |
| **`signature`**         | <code><a href="#base64urlstring">Base64URLString</a></code> |
| **`userHandle`**        | <code>string</code>                                         |


#### PublicKeyCredentialRequestOptionsJSON

A variant of PublicKeyCredentialRequestOptions suitable for JSON transmission to the browser to
(eventually) get passed into navigator.credentials.get(...) in the browser.

| Prop                   | Type                                                        |
| ---------------------- | ----------------------------------------------------------- |
| **`challenge`**        | <code><a href="#base64urlstring">Base64URLString</a></code> |
| **`allowCredentials`** | <code>PublicKeyCredentialDescriptorJSON[]</code>            |
| **`extensions`**       | <code>AuthenticationExtensionsClientInputs</code>           |


### Type Aliases


#### Base64URLString

An attempt to communicate that this isn't just any string, but a Base64URL-encoded string

<code>string</code>


#### AuthenticatorTransportFuture

A super class of TypeScript's `AuthenticatorTransport` that includes support for the latest
transports. Should eventually be replaced by TypeScript's when TypeScript gets updated to
know about it (sometime after 4.6.3)

<code>'ble' | 'internal' | 'nfc' | 'usb' | 'cable' | 'hybrid'</code>

</docgen-api>
