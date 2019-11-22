package zhan.rnadomgirdanimation.refresh

class SimpleDiffCallBack : IDiffCallBack<Int>() {

    override fun isItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldData!![oldItemPosition] === mNewData!![newItemPosition]
    }

    override fun isContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldData!![oldItemPosition] === mNewData!![newItemPosition]
    }
}
