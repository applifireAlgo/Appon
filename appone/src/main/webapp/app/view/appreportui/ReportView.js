Ext.define('Appone.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Appone.view.appreportui.ReportViewController',
	            'Appone.view.appreportui.datagrid.DataGridPanel',
	            'Appone.view.appreportui.datagrid.DataGridView',
	            'Appone.view.appreportui.querycriteria.QueryCriteriaView',
	            'Appone.view.appreportui.chart.ChartView',
	            'Appone.view.appreportui.datapoint.DataPointView',
	            'Appone.view.googlemaps.map.MapPanel',
	            'Appone.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
