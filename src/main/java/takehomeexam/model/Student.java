package takehomeexam.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message="first_name Cannot be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message="last_name Cannot be blank")
    private String lastName;

    @Column(name = "class")
    @NotBlank(message="class Cannot be blank")
    private String classRoom;

    @Column(name = "nationality")
    @NotBlank(message="nationality Cannot be blank")
    private String nationality;
}