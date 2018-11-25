public class Test4
{
    public static void main(String[] args)
    {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber)
            {
                System.out.println("OnSubscribe@ " + Thread.currentThread().getName());
                subscriber.onNext(1);
            }
        }).observeOn(Schedulers.io())
        .subscribe(new Subscriber<Integer>() {
            
            @Override
            public void onCompleted()
            {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable t)
            {
                System.out.println("onError");
            }

            @Override
            public void onNext(Integer var1)
            {
                System.out.println("Subscriber@ "+Thread.currentThread().getName());
                System.out.println(var1);
            }
        });
    }
}
