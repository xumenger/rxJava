public class Observable<T>
{
    final OnSubscribe<T> onSubscribe;

    private Observable(OnSubscribe<T> onSubscribe)
    {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe)
    {
        return new Observable<T>(onSubscribe);
    }

    public void subscribe(Subscriber<? super T> subscriber)
    {
        subscriber.onStart();
        onSubscribe.call(subscriber);
    }

    /**
     * map 操作符的作用是将T 类型的Event 转化为R 类型
     * 转化策略抽象成Transformer<T, R> 这一个函数接口，由外部传入
     * RxJava 中用的是Func1<T, R>
     */
    public <R> Observable<R> map(Transformer<? super T, ?extends R> transformer)
    {
        return create(new MapOnSubscribe<T, R>(this, transformer));
    }

    public interface OnSubscribe<T>
    {
        void call(Subscriber<? super T> subscriber);
    }

    public interface Transformer<T, R>
    {
        R call(T from);
    }
}

