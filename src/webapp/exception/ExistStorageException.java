package webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Резюме " + uuid + " уже существует в хранилище. SAVE impossible", uuid);
    }
}