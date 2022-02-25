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

# next step
- [ ] exception handling
  - [ ] developer error
    - invalid info -> do not redirect. show error message directly.
  - [ ] user denial of authorization.
    - valide redirect_uri, valid client_id but other problem -> redirect and add query string.
- [ ] client id should be string?
- [ ] separate authentication process and authorizaton process
- [ ] authorization code is only valid for one time usage
- [ ] create test

### decisions
- authorization code is just a random string rather than jwt
  - client doesn't need the details connected to it.
  - it is only for one time use.
  - server needs to save the code in order to check one time usage. (jwt might be better if server didn't save the code)
