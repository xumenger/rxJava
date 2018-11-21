public class Test2
{
    public static void main(String[] args)
    {
        Observable.create(new Observable.OnSubscribe<Integer>() 
        {
            @Override
            public void call(Subscriber<? super Integer> subscriber)
            {
                for (int i = 0; i < 10; i++)
                {
                    subscriber.onNext(i);
                }
            }
        }).map(new Observable.Transformer<Integer, String>()
        {
             @Override
             public String call(Integer from)
             {
                 return "maping " + from;
             }
        }).subscribe(new Subscriber<String>() 
        {
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
            public void onNext(String var1)
            {
                System.out.println(var1);
            }
        });
    }
}
