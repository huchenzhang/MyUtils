package com.example.huchenzhang.myutils.rxjava;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.huchenzhang.myutils.BaseActivity;
import com.example.huchenzhang.myutils.R;
import com.example.huchenzhang.myutils.databinding.RxJavaActivityBinding;
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
 */

public class RxJava extends BaseActivity {
	private int i = 1;
	private RxJavaActivityBinding binding;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = DataBindingUtil.setContentView(this,R.layout.rx_java_activity);
		initValue();
	}

	private void initValue() {
		final Timer timer = new Timer();
		//Observable的创建，被观察者
		Observable<Object> obServable = Observable.create(new ObservableOnSubscribe<Object>() {
			@Override
			public void subscribe(final ObservableEmitter<Object> e) throws Exception {
				//执行一些其他操作
				//*************
				//执行完毕后，触发回调，通知观察者
				timer.schedule(new TimeTask(e),1000,1000);//param:第一个long是首次执行的延时事件，第二个是每隔多长时间执行一次
			}
		});

		//Observer的创建
		Observer<Object> obServer = new Observer<Object>() {
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
		};

		obServable.subscribe(obServer);
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
