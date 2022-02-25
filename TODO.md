# authorization code
- [x] contains following information:
  - id
  - client_id
  - redirect_uri
  - user_id (to identify user)
  - expiration_date
- [x] set expiration time to 1 minute
- [x] validate redirect uri
- [x] longer, more random-like value

- [ ] receive scope
- [ ] receive state
- [ ] validate responseType

- [ ] authorization code is only valid for one time usage

# separate pages
- [x] separate authentication process and authorizaton process
- [x] create authorization form

# authorization page (not so important)
- [ ] show client info
- [ ] show what client asks for
- [ ] create random request id for authorization form
- [ ] validate random request id

- [ ] add Github login in authentication page

# authorization code exception (not so important)
- [ ] exception handling
  - [ ] developer error
    - invalid info -> do not redirect. show error message directly.
  - [ ] user denial of authorization.
    - valide redirect_uri, valid client_id but other problem -> redirect and add query string.

# login (not so important)
- [ ] give cookie with access token when authorized
- [ ] use the cookie to get the account info rather than session
- [ ] use the cookie first before authorizing via username and password

# access token
- [x] create jwt token
- [x] compare client secret

- [ ] compare client redirect url
- [ ] check grant_type
- [ ] contains following inforamation:
  - [ ] issuer (iss), authorization server identifier
  - [x] expires_at (exp)
  - [ ] audience (aud), resource server identifier
  - [x] subject (sub), user id
  - [ ] client id (client_id)
  - [x] issued_at (iat)
  - [ ] identifier of this token (jti)
  - [ ] scope (scope), the list of OAuth scopes this token includes

# token response
- [ ] access_token
- [ ] token type, which is "Bearer"
- [ ] expires_in
- [ ] include "Cache-Control: no-store" header

- [ ] refresh_token
- [ ] scope

# OpenId
- [ ] generate open id token

# refresh token
- [ ] generate refresh token

# account
- [ ] create registration page
- [ ] organize password related TODOs

- [ ] change email to username

# next step
- [ ] test with Spring Security client
- [ ] test with non Spring Security client
- [ ] create test
- [ ] clean code
  - [ ] Code class constructor -> static factory method
  - [ ] consider LoginResponse.of()
 
### decisions
- authorization code is just a random string rather than jwt
  - client doesn't need the details connected to it.
  - it is only for one time use.
  - server needs to save the code in order to check one time usage. (jwt might be better if server didn't save the code)
- `Cache-Control: no-store` header is to ensure clients do not cache access token request.
