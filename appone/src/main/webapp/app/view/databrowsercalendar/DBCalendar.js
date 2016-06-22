Ext.define('Appone.view.databrowsercalendar.DBCalendar', {
	extend : 'Appone.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Appone.view.databrowsercalendar.DBCalendarController',
	             'Appone.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	/*listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}*/
});
