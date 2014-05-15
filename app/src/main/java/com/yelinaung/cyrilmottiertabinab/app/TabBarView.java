/*
 * Copyright 2014 Ye Lin Aung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yelinaung.cyrilmottiertabinab.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Ye Lin Aung on 14/05/15.
 */
public class TabBarView extends LinearLayout {

  private static final int STRIP_HEIGHT = 6;

  public final Paint mPaint;

  private int mStripHeight;
  private float mOffset;
  private int mSelectedTab = -1;

  public TabBarView(Context context) {
    this(context, null);
  }

  public TabBarView(Context context, AttributeSet attrs) {
    this(context, attrs, android.R.attr.actionBarTabBarStyle);
  }

  public TabBarView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    mPaint = new Paint();
    mPaint.setColor(Color.WHITE);

    mStripHeight = (int) (STRIP_HEIGHT * getResources().getDisplayMetrics().density + .5f);
  }

  public void setStripColor(int color) {
    if (mPaint.getColor() != color) {
      mPaint.setColor(color);
      invalidate();
    }
  }

  public void setStripHeight(int height) {
    if (mStripHeight != height) {
      mStripHeight = height;
      invalidate();
    }
  }

  public void setSelectedTab(int tabIndex) {
    if (tabIndex < 0) {
      tabIndex = 0;
    }
    final int childCount = getChildCount();
    if (tabIndex >= childCount) {
      tabIndex = childCount - 1;
    }
    if (mSelectedTab != tabIndex) {
      mSelectedTab = tabIndex;
      invalidate();
    }
  }

  public void setOffset(float offset) {
    if (mOffset != offset) {
      mOffset = offset;
      invalidate();
    }
  }

  @Override
  protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    // Draw the strip manually
    final View child = getChildAt(mSelectedTab);
    if (child != null) {
      int left = child.getLeft();
      int right = child.getRight();
      if (mOffset > 0) {
        final View nextChild = getChildAt(mSelectedTab + 1);
        if (nextChild != null) {
          left = (int) (child.getLeft() + mOffset * (nextChild.getLeft() - child.getLeft()));
          right = (int) (child.getRight() + mOffset * (nextChild.getRight() - child.getRight()));
        }
      }
      canvas.drawRect(left, getHeight() - mStripHeight, right, getHeight(), mPaint);
    }
  }
}

