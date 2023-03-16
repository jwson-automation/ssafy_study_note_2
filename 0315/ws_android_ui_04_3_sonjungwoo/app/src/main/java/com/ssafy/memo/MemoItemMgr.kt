package com.ssafy.memo

object MemoItemMgr:IMemoItemMgr {
    override var memos = arrayListOf<MemoItem>()

    override fun getList(): ArrayList<MemoItem> {
        return memos
    }

    override fun size(): Int {
        return memos.size
    }

    override fun search(index: Int): MemoItem {
        return memos[index]
    }

    override fun add(m: MemoItem) {
        memos.add(m)
    }

    override fun update(index: Int, item: MemoItem) {
        memos[index] = item
    }

    override fun remove(index: Int) {
        memos.removeAt(index)
    }

    override fun clear() {
        memos.clear()
    }
}