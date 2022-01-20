package wooteco.idp.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wooteco.idp.domain.Code;
import wooteco.idp.domain.CodeRepository;

@Service
@AllArgsConstructor
public class CodeService {

    private final CodeRepository codeRepository;

    public Code createNewCode(Long registrationId, Long accountId) {
        return codeRepository.save(new Code(registrationId, accountId));
    }

    public Code findByCode(String value) {
        return codeRepository.findByValue(value).orElseThrow(RuntimeException::new);
    }
}
