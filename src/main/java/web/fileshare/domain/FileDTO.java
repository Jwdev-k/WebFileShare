package web.fileshare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class FileDTO {
    private int num;
    private String filename;
    private byte[] data;
    private int size;

    public FileDTO(int num, String filename, int size) {
        this.num = num;
        this.filename = filename;
        this.size = size;
    }
}
