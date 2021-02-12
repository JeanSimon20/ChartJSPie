package controlador;

import dao.CharUsuarioImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

@Named(value = "barcharC")
@SessionScoped
public class BarcharC implements Serializable {

    private List<Number> lstpersona;
    private CharUsuarioImpl dao = new CharUsuarioImpl();
    private PieChartModel pieModel = new PieChartModel();

    public BarcharC() {
    }

    @PostConstruct
    public void init() {
        try {
            lstpersona = dao.graficoPer();
            createPieModel();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void createPieModel() throws Exception{
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = lstpersona;
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Hombres");
        labels.add("Mujeres");
        data.setLabels(labels);

        pieModel.setData(data);
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public List<Number> getLstpersona() {
        return lstpersona;
    }

    public void setLstpersona(List<Number> lstpersona) {
        this.lstpersona = lstpersona;
    }

    public CharUsuarioImpl getDao() {
        return dao;
    }

    public void setDao(CharUsuarioImpl dao) {
        this.dao = dao;
    }

}
