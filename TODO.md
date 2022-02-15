# authorization code
- [x] set expiration time to 1 minute
- [x] validate redirect uri
- [ ] authorization code is only valid for one time usage
- [x] contains following information:
  - id 
  - client_id
  - redirect_uri
  - user_id (to identify user)
  - expiration_date
- [x] longer, more random-like value
- [ ] exception handling
  - [ ] developer error
    - invalid info -> do not redirect. show error message directly.
  - [ ] user denial of authorization.
    - valide redirect_uri, valid client_id but other problem -> redirect and add query string.
- [ ] create test

# client
- [ ] client id should be string?
