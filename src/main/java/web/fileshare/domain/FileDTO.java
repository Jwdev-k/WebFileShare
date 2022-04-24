package web.fileshare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class FileDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -8686912670594729934L;
    private int num;
    private String filename;
    private String dataPath;
    private int size;

    public FileDTO(int num, String filename, int size) {
        this.num = num;
        this.filename = filename;
        this.size = size;
    }
}
