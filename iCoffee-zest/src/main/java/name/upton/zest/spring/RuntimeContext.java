package name.upton.zest.spring;

public interface RuntimeContext {
    /**
     * 初始化运行环境
     */
    void doInit();

    /**
     * 销毁掉运行环境，释放占用的资源
     */
    void doDestory();
}
