public interface IState {
    void update();
    void startTransaciton();
    boolean isBusy();
    void isPumping();
    void endTransaction();

}


