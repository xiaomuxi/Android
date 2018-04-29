package com.project.archives.function.main.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.project.archives.R;
import com.project.archives.common.base.fragment.BaseActivityFragment;
import com.project.archives.common.utils.UIUtils;

import java.util.ArrayList;

/**
 * Created by inrokei on 2018/4/25.
 */

public class FirstFragment extends BaseActivityFragment {

    protected HorizontalBarChart mChart;
    protected HorizontalBarChart mChart2;
    protected Typeface mTfLight;
    private String[] xDataL = new String[] {"处分类", "初步核实类", "谈话函询类", "了结类", "暂存类"};
    private float[] yDataL = {10, 60, 70, 80, 30};
    ArrayList<BarEntry> yEntrys = new ArrayList<>();
    final ArrayList<String> xEntrys = new ArrayList<>();

    @Override
    protected View setContentView() {
        return UIUtils.inflate(mContext, R.layout.fragment_chart);
    }

    @Override
    protected void init() {
        mTfLight = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf");
    }

    @Override
    protected void initView(View view) {

        mChart = (HorizontalBarChart) view.findViewById(R.id.chart1);
        mChart2 = (HorizontalBarChart) view.findViewById(R.id.chart2);

        mChart.getDescription().setEnabled(false);
        mChart2.getDescription().setEnabled(false);

        setData();
        setChart();

//        XAxis xAxis = mChart.getXAxis();
//        xAxis.setGranularity(4f);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                LogUtils.i(TAG, value+"");
//                return null;
//            }
//        });
//
//        YAxis rightAxis = mChart.getAxisRight();
//        rightAxis.setEnabled(false);
//        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setEnabled(true);
//        leftAxis.setDrawZeroLine(true);
//        leftAxis.setGranularity(4f);
//        leftAxis.setDrawLabels(true);
//
//
//        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//
//        for (int i = 0; i < yDataL.length; i++) {
//            yEntrys.add(new BarEntry(i, yDataL[i]));
//            xEntrys.add(xDataL[i]);
//        }
//
//        BarDataSet set1 = new BarDataSet(yEntrys, "个数");
//        set1.setBarBorderWidth(3f);//设置线宽
//        BarData data = new BarData(set1);
////        data.setBarWidth(5f);
//        mChart.setData(data);
//
//        Legend l = mChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {

    }

    /*
    * 设置BarChart的数据
    * */
    private void setChart() {
        mChart.setDescription(null);                             //设置描述文字为null
        mChart.setFitBars(true);                                 //设置X轴范围两侧柱形条是否显示一半


        XAxis xAxis = mChart.getXAxis();                         //x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);             //设置label在底下
        xAxis.setDrawGridLines(false);                             //不设置竖型网格线
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(8);
        xAxis.setDrawLabels(true);                                 //是否显示X坐标轴上的刻度，默认是true
        xAxis.setLabelCount(5, false);                             //第一个参数是轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        //自定义x轴显示的数字格式
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xDataL));



        YAxis rightAxis = mChart.getAxisRight();              //获取到y轴，分左右
        rightAxis.setLabelCount(5, false);                      //第一个参数是轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        rightAxis.setGridColor(Color.parseColor("#000000"));   //设置横网格颜色
        rightAxis.setDrawTopYLabelEntry(false);
        rightAxis.setDrawGridLines(true);
        rightAxis.setDrawLabels(true);
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setDrawAxisLine(true);                      //设置为true,绘制轴线
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);       //y轴的数值显示在外侧
        //这里也可以自定义y轴显示样式。和x轴的自定义方法一样

        mChart.getAxisLeft().setEnabled(false); // 隐藏右边 的坐标轴
        Legend legend = mChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例在图表上方

        mChart.setTouchEnabled(false);
        mChart.animateY(1500);
    }

    /*
    * 准备数据，Barchart设置数据
    * 设置数据需要四个步骤
    * */
    private void  setData(){
        //1,BarEntry集合,
        ArrayList<BarEntry> barEntriesData = new ArrayList<>();

        for(int i=0;i<yDataL.length;i++){
            barEntriesData.add(new BarEntry(i, yDataL[i]));
        }

        //2，BarDataSet
        BarDataSet bardataSet = new BarDataSet(barEntriesData,"个数");
        bardataSet.setDrawValues(false);
        //3，把BarDataSet数据添加到IBarDataSet集合
        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
        iBarDataSets.add(bardataSet);

        //4,BarData
        BarData barData = new BarData(iBarDataSets);

        //5,设置数据
        mChart.setData(barData);
    }
}

