/*
 * Fib.java
 *
 * MIT License
 *
 * Copyright (c) 2020 DJ-Zuan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.dj.zuan.dsa.fib;

/**
 * 清华大学 《数据结构习题解析》 第16页 [1-22] 源码及注释改版
 *
 * @author DJ-Zuan on 2020-10-27
 *
 */
public class Fib {

    /**
     * 当前Fibonacci项的值
     */
    private long cur;

    /**
     * 上一个Fibonacci项的值
     */
    private long prev;

    /**
     * 初始化为不小于n的最小Fibonacci项（如，Fib(6) = 8），O(logΦ(n))时间
     *
     * @param n
     */
    public Fib(long n) {
        prev = 1; cur = 0; //初始化：fib(-1)、fib(0)
        while (cur < n) next(); //O(logΦ(n))时间
    }

    /**
     * 获取当前Fibonacci项（如，若当前为8，则返回8），O(1)时间
     *
     * @return
     */
    public long get() {
        return cur;
    }

    /**
     * 转至下一个Fibonacci项（如，若当前为8，则转至13），O(1)时间
     * @return
     */
    public long next() {
        cur += prev; prev = cur - prev;
        return cur;
    }

    /**
     * 转至上一个Fibonacci项（如，若当前为8，则转至5），O(1)时间
     * @return
     */
    public long prev() {
        prev = cur - prev; cur -= prev;
        return cur;
    }
}
