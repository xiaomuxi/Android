package com.project.archives.function.main.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
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
import com.project.archives.common.dao.manager.CaseInvesManager;
import com.project.archives.common.dao.manager.EndingsManager;
import com.project.archives.common.dao.manager.LettersManager;
import com.project.archives.common.dao.manager.VerificationsManager;
import com.project.archives.common.dao.manager.ZancunsManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by inrokei on 2018/4/25.
 */

public class HomeFragment extends BaseActivityFragment {

    protected HorizontalBarChart mChart;
    protected BarChart mBarChart;
    protected Typeface mTfLight;
    private String[] xDataL = new String[] {"处分类", "初步核实类", "谈话函询类", "了结类", "暂存类"};
    private float[] yDataL = {0, 0, 0, 0, 0};

    private String[] xDataL2 = new String[] {"2018-01", "2018-02", "2018-03"};

    ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals4 = new ArrayList<BarEntry>();
    ArrayList<BarEntry> yVals5 = new ArrayList<BarEntry>();

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
        mBarChart = (BarChart) view.findViewById(R.id.chart2);

        mChart.getDescription().setEnabled(false);
        mBarChart.getDescription().setEnabled(false);

        initData();
        setData();
        setChart();
    }

    @Override
    protected void initCreated(Bundle savedInstanceState) {

    }

    private void initData() {
        yDataL[0] = CaseInvesManager.getInstance().getCount();
        yDataL[1] = VerificationsManager.getInstance().getCount();
        yDataL[2] = LettersManager.getInstance().getCount();
        yDataL[3] = EndingsManager.getInstance().getCount();
        yDataL[4] = ZancunsManager.getInstance().getCount();


        Calendar gc = new GregorianCalendar();
        gc.set(Calendar.DAY_OF_MONTH, 1);
        Date monthStart = gc.getTime();
        gc.add(Calendar.MONTH, 1);
        gc.add(Calendar.DAY_OF_MONTH, -1);
        Date monthEnd = gc.getTime();

        for (int i = 0; i < 3; i ++) {

            xDataL2[i] = getMonty(-i + 1);
            Date startTime =  getFirstDayOfMonth(-i + 1);
            Date endTime =  getLastDayOfMonth(-i + 1);

            float caseInves = CaseInvesManager.getInstance().getCountByQuery(startTime, endTime);
            float verifications = VerificationsManager.getInstance().getCountByQuery(startTime, endTime);
            float letters = LettersManager.getInstance().getCountByQuery(startTime, endTime);
            float endings = EndingsManager.getInstance().getCountByQuery(startTime, endTime);
            float zancuns = ZancunsManager.getInstance().getCountByQuery(startTime, endTime);

            yVals1.add(new BarEntry(i, caseInves));
            yVals2.add(new BarEntry(i, verifications));
            yVals3.add(new BarEntry(i, letters));
            yVals4.add(new BarEntry(i, endings));
            yVals5.add(new BarEntry(i, zancuns));
        }

    }

    private String getMonty(int monthAmount) {
        Calendar gc = new GregorianCalendar();
        gc.add(Calendar.MONTH, monthAmount);
        return gc.get(Calendar.YEAR) + "-" + gc.get(Calendar.MONTH);
    }

    private Date getFirstDayOfMonth(int monthAmount) {
        Calendar gc = new GregorianCalendar();
        gc.add(Calendar.MONTH, monthAmount);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        return gc.getTime();
    }

    private Date getLastDayOfMonth(int monthAmount) {
        Calendar gc = new GregorianCalendar();
        gc.add(Calendar.MONTH, monthAmount);
        gc.set(Calendar.DAY_OF_MONTH, -1);
        return gc.getTime();
    }

    /*
    * 设置BarChart的数据
    * */
    private void setChart() {
        mChart.setFitBars(true);                                 //设置X轴范围两侧柱形条是否显示一半
        mChart.setDrawValueAboveBar(true);
        mChart.setPinchZoom(false);

        XAxis xAxis = mChart.getXAxis();                         //x轴
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);             //设置label在底下
        xAxis.setDrawGridLines(false);                             //不设置竖型网格线
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(10f);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(5, false);                             //第一个参数是轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        //自定义x轴显示的数字格式
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xDataL));



        YAxis rightAxis = mChart.getAxisRight();              //获取到y轴，分左右
        rightAxis.setLabelCount(5, false);                      //第一个参数是轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        rightAxis.setGridColor(Color.parseColor("#000000"));   //设置横网格颜色
        rightAxis.setDrawTopYLabelEntry(true);
        rightAxis.setDrawGridLines(true);
        rightAxis.setGranularity(1f);
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);       //y轴的数值显示在外侧
        //这里也可以自定义y轴显示样式。和x轴的自定义方法一样

        mChart.getAxisLeft().setEnabled(false);
        Legend legend = mChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例在图表上方

        mChart.setTouchEnabled(false);
        mChart.animateY(1500);


        XAxis xAxis2 = mBarChart.getXAxis();                         //x轴
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);             //设置label在底下
        xAxis2.setDrawGridLines(false);                             //不设置竖型网格线
        xAxis2.setTextColor(Color.BLACK);
        xAxis2.setTextSize(10f);
        xAxis2.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis2.setLabelCount(3, false);                             //第一个参数是轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        //自定义x轴显示的数字格式
        xAxis2.setValueFormatter(new IndexAxisValueFormatter(xDataL2));


        YAxis letftAxis = mBarChart.getAxisLeft();              //获取到y轴，分左右
        letftAxis.setLabelCount(8, false);                      //第一个参数是轴坐标的个数，第二个参数是 是否不均匀分布，true是不均匀分布
        letftAxis.setGridColor(Color.parseColor("#000000"));   //设置横网格颜色
        letftAxis.setDrawTopYLabelEntry(true);
        letftAxis.setDrawGridLines(true);
        letftAxis.setGranularity(1f);
        letftAxis.setAxisMinimum(0f);
        letftAxis.setTextColor(Color.BLACK);
        letftAxis.setSpaceBottom(15f);
        letftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);       //y轴的数值显示在外侧
        //这里也可以自定义y轴显示样式。和x轴的自定义方法一样

        mBarChart.getAxisRight().setEnabled(false);
        Legend legend2 = mBarChart.getLegend();
        legend2.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例在图表上方

        mBarChart.setTouchEnabled(false);
        mBarChart.animateY(1500);
    }

    /*
    * 准备数据，Barchart设置数据
    * 设置数据需要四个步骤
    * */
    private void  setData(){
        // barchart set data

        BarDataSet barSet1 = new BarDataSet(yVals1, "处分类");
        barSet1.setColor(Color.RED);
        BarDataSet barSet2 = new BarDataSet(yVals2, "初步核实类");
        barSet2.setColor(Color.BLUE);
        BarDataSet barSet3 = new BarDataSet(yVals3, "谈话函询类");
        barSet3.setColor(Color.GREEN);
        BarDataSet barSet4 = new BarDataSet(yVals4, "了结类");
        barSet4.setColor(Color.GRAY);
        BarDataSet barSet5 = new BarDataSet(yVals5, "暂存类");
        barSet5.setColor(Color.YELLOW);

        BarData data2 = new BarData(barSet1, barSet2, barSet3, barSet4, barSet5);
        data2.setBarWidth(0.08f);
        mBarChart.setData(data2);
        mBarChart.groupBars(0, 0.1f, 0.05f);

        //1,BarEntry集合,
        ArrayList<BarEntry> barEntriesData = new ArrayList<>();

        for(int i=0;i<yDataL.length;i++){
            barEntriesData.add(new BarEntry(i, yDataL[i]));
        }

        //2，BarDataSet
        BarDataSet bardataSet = new BarDataSet(barEntriesData,"个数");
        //3，把BarDataSet数据添加到IBarDataSet集合
        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
        iBarDataSets.add(bardataSet);

        //4,BarData
        BarData barData = new BarData(iBarDataSets);
        barData.setValueTextSize(10f);
        //5,设置数据
        mChart.setData(barData);
    }
}

