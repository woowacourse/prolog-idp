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

# separate pages
- [ ] separate authentication process and authorizaton process
- [ ] create authorization form
- [ ] Code class constructor -> static factory method

# authorization page
- [ ] show client info
- [ ] show what client asks for
- [ ] create random request id for authorization form
- [ ] validate random request id

# authorization code exception
- [ ] exception handling
  - [ ] developer error
    - invalid info -> do not redirect. show error message directly.
  - [ ] user denial of authorization.
    - valide redirect_uri, valid client_id but other problem -> redirect and add query string.

# login (not so important)
- [ ] give cookie with access token when authorized
- [ ] use the cookie to get the account info rather than session
- [ ] use the cookie first before authorizing via username and password

# next step
- [ ] client id should be string?
- [ ] authorization code is only valid for one time usage
- [ ] change email to username
- [ ] create test

### decisions
- authorization code is just a random string rather than jwt
  - client doesn't need the details connected to it.
  - it is only for one time use.
  - server needs to save the code in order to check one time usage. (jwt might be better if server didn't save the code)
