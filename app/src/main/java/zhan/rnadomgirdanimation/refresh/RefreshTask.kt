package zhan.rnadomgirdanimation.refresh

import android.os.Handler

import java.lang.ref.WeakReference

class RefreshTask(helper: RefreshHelper<*>, handler: Handler) : Runnable {

    private val mWeakRefHelper: WeakReference<RefreshHelper<*>> = WeakReference(helper)
    private val mWeakRefHandler: WeakReference<Handler> = WeakReference(handler)

    override fun run() {
        val helper = mWeakRefHelper.get()
        if (helper != null) {
            helper.generateNewData()

            val handler = mWeakRefHandler.get()
            if (handler != null && helper.isRunning) {
                handler.postDelayed(this, helper.refreshTime.toLong())
            }
        }
    }
}
