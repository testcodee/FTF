package com.tft.forthefuture.User.Vo;

import lombok.*;

import java.time.LocalDateTime; // TIMESTAMP 타입을 매핑하기 위한 임포트
import java.io.Serializable; // VO가 직렬화 가능하도록 선언 (선택적이지만 일반적으로 권장)

@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 한 번에 추가해줍니다.
@NoArgsConstructor // 파라미터가 없는 기본 생성자를 자동으로 생성합니다.
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자를 자동으로 생성합니다.
public class User implements Serializable { // Serializable 인터페이스 구현은 선택사항이지만, 객체를 직렬화해야 하는 경우 (예: 세션 저장, 캐싱 등)에 유용합니다.

    //index
    private Long id;

    // 아이디
    private String username;

    // 이름
    private String name;

    // 별명
    private String nickname;

    // 비밀번호
    private String password;

    // 이메일
    private String email;

    // 기타 필드들...
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
