package ir.MrMohamadHosein.learnrxjavacreate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io

class MainActivity : AppCompatActivity() {
    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable
            .just(16, 20, 14, 17, 15, 19, 12, 15)
            .switchMap {
                Observable.just(it * 100)
            }
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Int) {
                    Log.v("testLog", t.toString())
                }

                override fun onError(e: Throwable) {
                    Log.v("testLog", e.message!!)
                }

                override fun onComplete() {

                }

            })


        // -----------------------------

//        getDataOneByOne()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map {
//
//            }
//            .filter {
//
//            }
//            .takeLast(3)
//            .subscribe( object :Observer<Int> {
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onNext(t: Int) {
//
//                }
//
//                override fun onError(e: Throwable) {
//
//                }
//
//                override fun onComplete() {
//
//                }
//
//
//            } )


    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    fun getAllData(): Single<List<Int>> {
        return Single.create { emitter ->
            // data is ready
            emitter.onSuccess(listOf(1, 4, 5))
        }
    }

    fun getDataOneByOne(): Observable<Int> {
        val data = listOf(1, 4, 5, 8, 7, 14, 15, 16, 20)
        return Observable.create { emitter ->
            data.forEach {
                emitter.onNext(it)
            }
        }
    }

}