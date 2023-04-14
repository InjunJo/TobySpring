package user.domain;

import lombok.*;
import user.myJpa.MyEntity;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {

    private Integer tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;

}
