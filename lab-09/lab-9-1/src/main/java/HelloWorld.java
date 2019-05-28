import io.reactivex.*;
import io.reactivex.subjects.BehaviorSubject;

public class HelloWorld {
    public static void main(String[] args) {
        BehaviorSubject<Integer> a = BehaviorSubject.create();
        BehaviorSubject<Integer> b = BehaviorSubject.create();
        Observable<Integer> c = Observable.combineLatest(a,b,(i1,i2) -> i1+i2);
        c.subscribe (System.out::println);
        a.onNext(10);
        b.onNext(10);
        a.onNext(20);

        Observable<Integer> cIn2 = c.map(i->i*i);
        cIn2.subscribe (System.out::println);
        a.onNext(1);
        b.onNext(2);
        a.onNext(3);
        Observable<Integer> cEven = c.filter(i->i%2==0); cEven.subscribe (System.out::print);
    }
}