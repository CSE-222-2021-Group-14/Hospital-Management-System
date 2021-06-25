import java.io.Serializable;

enum StatusType implements Serializable {
    EMPTY,
    TAKEN,
    CANCELLED,
    FINISHED
}