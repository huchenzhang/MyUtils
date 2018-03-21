package com.example.huchenzhang.myutils.rxjava;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.RxJavaActivityBinding;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * http://www.jianshu.com/p/d149043d103a
 * RxJava
 * Created by hu on 2017/5/2.
 * 一个被观察者可以有多个观察者
 */

public class RxJava extends BaseActivity {
	private int i = 1;
	private RxJavaActivityBinding binding;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this,R.layout.rx_java_activity);
		initValue1();
//		initValue2();
	}

	/** 测试二 **/
	private void initValue2(){
		Integer[] numbers = {1,2,3,4,5,6,7};
		List<Integer> lists = Arrays.asList(numbers);
	}



	/** 测试一 **/
	private void initValue1() {
		final Timer timer = new Timer();
		//Observable的创建，被观察者
		//Observable的创建方式有很多种，见官网：https://github.com/ReactiveX/RxJava/wiki/Creating-Observables
		Observable.create(new ObservableOnSubscribe<Object>() {
			//subscribe()有多个重载的方法
			@Override
			public void subscribe(final ObservableEmitter<Object> e) throws Exception {
				//执行一些其他操作
				//*************
				//执行完毕后，触发回调，通知观察者
				timer.schedule(new TimeTask(e),1000,1000);
				//param:第一个long是首次执行的延时事件，第二个是每隔多长时间执行一次
			}
		}).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object s) {

                if(i >= 10){
                    timer.cancel();//终止定时器
                }
                changeTextView(s.toString());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }
        });

		//Observer的创建
//		Observer<Object> obServer = new Observer<Object>() {
//			@Override
//			public void onSubscribe(Disposable d) {
//
//			}
//
//			@Override
//			public void onNext(Object s) {
//
//				if(i >= 10){
//					timer.cancel();//终止定时器
//				}
//				changeTextView(s.toString());
//			}
//
//			@Override
//			public void onError(Throwable e) {
//				e.printStackTrace();
//			}
//
//			@Override
//			public void onComplete() {
//			}
//		};

		//通过Subscribe绑定两者关系
//		obServable.subscribe(obServer);
		//指定线程，被观察者指定为子线程，观察者指定为主线程
		//subscribeOn()指定上游线程，第一次有效，多次指定以第一次指定为准
		//observeOn()下游线程，多次指定，最后一次有效
//		obServable
// 				.subscribeOn(Schedulers.newThread())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(obServer);
	}

	/***
	 * 接受到消息时，通知ui线程更新
	 */
	private void changeTextView(final String str) {
		//观察者接收到通知，进行相关操作
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if(binding.tvRx2 == null){
					return ;
				}
				binding.tvRx2.setText(str+(i==0?"":i));
				i = ++i;
			}
		});
	}

	/***
	 * 创建定时器去通知ui线程
	 */
	private class TimeTask extends TimerTask{
		private  ObservableEmitter<Object> e;
		TimeTask(ObservableEmitter<Object> e){
			this.e = e;
		}

		@Override
		public void run() {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if(binding.tvRx1 == null){
						return;
					}
					binding.tvRx1.setText("我来发射数据" + (i==0?"":i));
					e.onNext("我来发射数据" + (i==0?"":i));
				}
			});
		}
	}
}
