/*
 * PermuteUtils.java
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

package com.dj.zuan.dsa.vector.utils.permute;

import com.dj.zuan.dsa.vector.IntVector;

import java.util.Random;

/**
 * 置乱器
 *
 * @author DJ-Zuan on 2020-11-04
 *
 */
public class PermuteUtils {

    public static void permute(IntVector v) { //随机置乱向量，使各元素等概率出现于各位置
        Random r = new Random();
        for (int i = v.size(); i > 0; i--) { //自后向前
            int j = r.nextInt(i); //[0, i)中随机一个带交换元素的秩
            int tmp = v.get(i - 1);
            v.put(i - 1, v.get(j));
            v.put(j, tmp); //v[i - 1]与随机秩交换
        }
    }
}
