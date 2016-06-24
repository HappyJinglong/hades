/**function postCodeIfValid(value)
{
	var reg = /^[0-9]{6}$/;
	return reg.test(value);
}
Ext.apply(Ext.form.VTypes, {
	postCode:function (value, field)
	{
		var tmpValue = Ext.util.Format.trim(value); 
		if ( tmpValue == '')
		{
			return false;
		}
		else
		{
			if (!postCodeIfValid(tmpValue))
			{
				return false;
			}
			return true;
		}
	},
	postCodeText : '机构邮编只能由六位数字组成。'
});*/
Ext.tree.TreeLoader.prototype.createNode = function(attr) {
	if (this.baseAttrs) {
		Ext.applyIf(attr, this.baseAttrs);
	}
	if (this.applyLoader !== false) {
		attr.loader = this;
	}
	if (typeof attr.uiProvider == 'string') {
		attr.uiProvider = this.uiProviders[attr.uiProvider]
				|| eval(attr.uiProvider);
	}
	var node = (attr.leaf
			? new Ext.tree.TreeNode(attr)
			: new Ext.tree.AsyncTreeNode(attr));
	node.kindcode = attr.kindcode;
	node.keyword = attr.keyword;
	node.kinddesc = attr.kinddesc;
	return node;
	
}

Ext.tree.MenuTree = Ext.extend(Ext.tree.TreePanel, {
	animate : false,
	rootVisible : true,
	autoScroll : true,
	rootId : 'root',
	rootText : '社区课程资源',
	baseCls : '', // 替换默认的x-panel,背景为透明色；
	cmenu : null, // 菜单
	editWin : null, // 添加或编辑窗口
	treeTypeFlag: false,
	menuItemAdd : function(item, e) {
		var node = item.parentMenu.node;
		// 对该节点进行添加处理
		//var nodename = node.ownerTree.getNodeName(node.getDepth() + 1) + '名称';
		var nodename = '分类名称';
		var win = node.ownerTree.createWin(nodename);
		// 设置form值
		win.form.getForm().setValues(this.emptyObj);
		win.setTitle('增加');
		win.show();
		win.center();
		win.form.url = win.parent.addUrl;
		win.form.getForm().setValues({
			depth : node.getDepth(),
			nodeid : node.id,
			nodename : '',
			kindcode:''
		});
		
		win.reloadnode = node;
	},
	menuItemEdit : function(item, e) {
		var node = item.parentMenu.node;
		// 对该节点进行编辑处理
		//var nodename = node.ownerTree.getNodeName(node.getDepth()) + '名称';
		var nodename = '分类名称'; 
		var win = node.ownerTree.createWin(nodename);
		// 设置form值
		win.form.getForm().setValues(this.emptyObj);
		win.setTitle('修改');
		win.show();
		win.center();
		win.form.url = win.parent.updateUrl;
		win.form.getForm().setValues({
			depth : node.getDepth(),
			nodeid : node.id,
			nodename : node.text,
			kindcode : node.kindcode,
			keyword : node.keyword,
			kinddesc : node.kinddesc
		});
		win.reloadnode = node.parentNode;
	},
	menuItemDel : function(item, e) { 
		var node = item.parentMenu.node;
		// 对该节点进行删除处理
		Ext.Msg.confirm('删除', '是否删除课程分类:'+ node.text + '?', function(button) {
			if (button == 'yes') {
				// 发送删除数据的请求
				Ext.Ajax.request({
					url : node.ownerTree.deleteUrl,
					params : {
						depth : node.getDepth(),
						nodeid : node.id
					},
					failure : function() {						
						var msg = '删除失败!';
						Ext.Msg.alert('删除', msg);
					},
					success : function(response,options) {
						//从服务器返回结果，根据response中的success判断
						var o=eval("("+response.responseText+")");
						var msg = '删除失败!';
						if(o.success){
							node.parentNode.reload();
						}else{
							if(o.msg){
								msg=o.msg;
							}
							Ext.Msg.alert('删除', msg);
						}
					}
				});
			}
		})
	},
	menuItemInsert:function(item, e) {
		var node = item.parentMenu.node;
		// 在当前节点后面插入一条新节点
		var nodename = node.ownerTree.getNodeName(node.getDepth()) + '名称';
		var win = node.ownerTree.createWin(nodename);
		// 设置form值
		win.form.getForm().setValues(this.emptyObj);
		win.setTitle('增加');
		win.show();
		win.center();
		win.form.url = win.parent.insertUrl;
		win.form.getForm().setValues({
			depth : node.getDepth(),
			nodeid : node.id,
			nodename : ''
		});
		win.reloadnode = node.parentNode;
	},
	menuItemMoveUp:function(item, e) {
		var node = item.parentMenu.node;
		// 发送上移请求
		Ext.Ajax.request({
			url : node.ownerTree.moveUrl,
			params : {
				depth : node.getDepth(),
				nodeid : node.id,
				move:'up'
			},
			failure : function() {						
				var msg = '上移失败!';
				Ext.Msg.alert('上移', msg);
			},
			success : function(response,options) {
				//从服务器返回结果，根据response中的success判断
				var o=eval("("+response.responseText+")");
				var msg = '上移失败!';
				if(o.success){
					node.parentNode.reload();
				}else{
					if(o.msg){
						msg=o.msg;
					}
					Ext.Msg.alert('上移', msg);
				}
			}
		});
	},
	menuItemMoveDown:function(item, e) {
		var node = item.parentMenu.node;
		// 发送下移请求
		Ext.Ajax.request({
			url : node.ownerTree.moveUrl,
			params : {
				depth : node.getDepth(),
				nodeid : node.id,
				move:'down'
			},
			failure : function() {						
				var msg = '下移失败!';
				Ext.Msg.alert('下移', msg);
			},
			success : function(response,options) {
				//从服务器返回结果，根据response中的success判断
				var o=eval("("+response.responseText+")");
				var msg = '下移失败!';
				if(o.success){
					node.parentNode.reload();
				}else{
					if(o.msg){
						msg=o.msg;
					}
					Ext.Msg.alert('下移', msg);
				}
			}
		});
		
	},
	createWin : function(nodename) {
		var win = null;
		var form = new Ext.FormPanel({
			url : '',
			labelWidth : 75,
			frame : true,
			bodyStyle : 'padding:5px 5px 0',
			defaults : {
				width : 170
			},
			defaultType : 'textfield',
			items : [{
				xtype : 'hidden',
				fieldLabel : '当前节点层次',
				name : 'depth',
				allowBlank : false
			}, {
				xtype : 'hidden',
				fieldLabel : '节点ID',
				name : 'nodeid'
			}, {
				id : 'nodename',
				fieldLabel : nodename ? nodename : '课程分类名称',
				name : 'nodename',
				maxLength:this.maxLength || 64,
				maxLengthText:(nodename?nodename:'课程分类名称名称')+'不能超过{0}个字符.',
				blankText:(nodename?nodename:'课程分类名称')+'为必填项.',
				allowBlank : false
			}
			]
		});
	win = new Ext.Window({
			modal : true,
			layout : 'fit',
			width : 300,
			height : 140,
			plain : true,
			title : '添加',
			items : form,
			buttons : [{
				text : '确定',
				handler : function() {
					win.parent.saveform(win);
				}
			}, {
				text : '关闭',
				handler : function() {
					win.destroy();
				}
			}],
			keys:{
				key:[10,13],
				fn:function() {
					win.parent.saveform(win);
				}
			}
		});
		win.form = form;
		// this.editWin = win;
		win.parent = this;
		win.on("show",function(){
			setTimeout(function(){Ext.getCmp("nodename").focus()},100);
		});
		return win;
	},
	// 保存
	saveform : function(w) {
	
		w.form.getForm().submit({
			url : w.form.url,
			method : 'POST',
			waitTitle: '请等待',
			waitMsg : '正在保存数据......',
			success : function(form, action) {
				w.destroy();
				
				// 保存成功，刷新树节点
				if (w.parent.reloadtree)
					w.parent.reloadtree(w.reloadnode);
			},
			failure : function(form, action) {
				var msg = '数据保存失败!';
				if (action.failureType == 'client') {
					var field=w.form.getForm().findField('nodename');
					msg = field.el.dom.qtip;
					
				} else {
					if (action.result.msg)
						msg = action.result.msg;
				}
				Ext.Msg.alert('消息框', msg);
			}
		});
	},
	// 刷新节点
	reloadtree : function(node) {
		try{
		return node.reload();
		}catch(e){
		}
	},
	// 根据roleItems中的id，将总菜单中的定义添加到items中
	addItemTo : function(items, roleItems) {
		for (var i = 0; i < roleItems.length; i++) {
			// 查找菜单定义中的id是否在roleItems中
			if (roleItems[i] == '-') {
				items.push(roleItems[i]);
				continue;
			}
			for (var j = 0; j < this.menuItems.length; j++) {
				if (this.menuItems[j].id == roleItems[i])
					items.push(this.menuItems[j]);
			}
		}
	},
	// 根据节点深度返回节点名称
	getNodeName : function(depth) {
		if (this.nodeNames) {
			for (var i = 0; i < this.nodeNames.length; i++) {
				var o = this.nodeNames[i];
				if (o[0] == depth)
					return o[1];
			}
		}
		return null;
	},
	// 显示菜单前，根据规则定义菜单项
	onBeforecontextmenu : function(tree, node) {
		// 根据参数判断cmenu的显示内容
		var depth = node.getDepth();
//		if (depth == 0)
//		{
//			return false;
//		}
		// 判断menuRoles
		// 定义空菜单数组
		var citems = [];
		var roles = this.menuRoles;
		if (roles)
		{
			if(depth == 0){
				this.addItemTo(citems, roles[0].menuItems);
			}
			else{
				for (var i = 0; i < roles.length; i++) {
					// 查找菜单规则
					var role = roles[i];
					//for (var j in role.depth) {
					//if (role.depth[j] == depth) {
							// 当前节点的深度在规则中，将规则定义的菜单id对应的菜单项添加到菜单数组中
							this.addItemTo(citems, role.menuItems);
						//}
					//}
							
				};
			}
			
		}
//		// 如果没有生成菜单项，则返回false，不显示菜单
//		if (citems.length == 0)
//			return true;
		this.cmenu = new Ext.menu.Menu({
			items : citems,
			node : node
		});
		// 显示菜单前检查该节点是否有子节点
		this.cmenu.on('beforeshow', function() {
			var items = this.cmenu.items.items;
			var node = this.cmenu.node;
			var isExpanded = node.expanded;
			var f = function() {
				if (node.hasChildNodes()) {
					// 有子节点，不能删除
					for (var i = 0; i < items.length; i++) {
						if (items[i].id == 'del'){
							//items[i].setDisabled(true);
						}
					}
				}
				if (node.isFirst()){
					//是第一个节点，不能上移
					for (var i = 0; i < items.length; i++) {
						if (items[i].id == 'moveup')
							items[i].setDisabled(true);
					}
				}
				if (node.isLast()){
					//是最后一个节点，不能下移
					for (var i = 0; i < items.length; i++) {
						if (items[i].id == 'movedown')
							items[i].setDisabled(true);
					}
				}
				if (!isExpanded)
					node.collapse();
			};
			node.expand(false, false, f);
		}, this);
	},
	onContextmenu : function(node, e) {
		node.select();
		if (this.fireEvent('beforecontextmenu', this, node) != false) {
			e.stopEvent();
			if (!this.cmenu.el) {
				this.cmenu.render();
			}
			var xy = e.getXY();
			this.cmenu.showAt(xy);
		}
	},
	initComponent : function() {
		this.loader = new Ext.tree.TreeLoader({
			url : this.dataUrl
		});
		this.root = new Ext.tree.AsyncTreeNode({
			id : this.rootId,
			text : this.rootText
		});
		this.menuItems = [{
			id : 'add',
			text : '增加',
			handler : this.menuItemAdd
		}, {
			id : 'edit',
			text : '修改',
			handler : this.menuItemEdit
		}, {
			id : 'del',
			text : '删除',
			handler : this.menuItemDel
		},{
			id : 'insert',
			text : '插入',
			handler : this.menuItemInsert
		},{
			id : 'moveup',
			text : '上移',
			handler : this.menuItemMoveUp
		},{
			id : 'movedown',
			text : '下移',
			handler : this.menuItemMoveDown
		}];
		// 如果指定了颜色，则设置背景成指定的颜色
		if (this.bgcolor)
			this.bodyStyle = 'background-color:' + this.bgcolor;
		if (this.beforeload)
			this.loader.on("beforeload", this.beforeload);
		this.on('contextmenu', this.onContextmenu, this);
		Ext.tree.MenuTree.superclass.initComponent.call(this);
		this.addEvents('beforecontextmenu');

	},
	initEvents : function() {
		Ext.tree.MenuTree.superclass.initEvents.call(this);
		this.on('beforecontextmenu', this.onBeforecontextmenu, this);
	},
	onRender : function(ct, position) {
		Ext.tree.MenuTree.superclass.onRender.call(this, ct, position);
		// 增加对齐方式，避免在center或right的时候，树显示不正确
		this.el.dom.align = "left";
	},
	dispatcherurl : function(e,ctx)
	{
		id = e.attributes['id'];
		type = id.split("_")[1];
		if('root' == id)
		{
		}else if('0' == type)
		{
			this.translateschool(ctx,id);
		}else if('1' == type)
		{
			
			this.translategrade(ctx,id);
		}else if('2' == type)
		{
			this.translateclass(ctx,id);
		}else
		{
			this.translatestudetail(ctx,id);
		}
	},
	translateschool : function(ctx,id)
	{
		parent.document.getElementById('a').src=ctx+"/cmis3711/SchoolAction.a?levelId="+id.split("_")[0];
	},
	translategrade : function(ctx,id)
	{
		parent.document.getElementById('a').src=ctx+"/cmis37/GradeAction.a?gradeId ="+id.split("_")[0];
	},
	translateclass : function(ctx,id)
	{
		parent.document.getElementById('a').src=ctx+"/cmis37/ClassesAction.a?classId="+id.split("_")[0];
	},
	translatestudetail : function(ctx,id)
	{
		parent.document.getElementById('a').src=ctx+"/disparcher/a.stu?stuId="+id.split("_")[0];
	}
})