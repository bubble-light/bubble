/**
 * 菜单数据
 */
Hdap.menuData = [ {
	"id" : "root",
	"name" : "统计中心",
	"type" : "leaf",
	"icon" : "dashboard.png",
	"src" : "index.html"
}, {
	"id" : "statistics",
	"name" : "专题统计",
	"type" : "folder",
	"icon" : "layered.png",
	"children" : [ {
		"id" : "browser",
		"name" : "专题浏览",
		"type" : "leaf",
		"icon" : "",
		"src" : "#"
	}, {
		"id" : "transfer",
		"name" : "专题转发",
		"type" : "folder",
		"icon" : "layered.png",
		"children" : [ {
			"id" : "appTransfer",
			"name" : "APP转发",
			"type" : "leaf",
			"icon" : "",
			"src" : "#"
		}, {
			"id" : "weChatTransfer",
			"name" : "微信转发",
			"type" : "leaf",
			"icon" : "",
			"src" : "#"
		} ]
	}, {
		"id" : "mark",
		"name" : "专题点赞",
		"type" : "leaf",
		"icon" : "",
		"src" : "#"
	}, {
		"id" : "mark",
		"name" : "专题收藏",
		"type" : "leaf",
		"icon" : "",
		"src" : "#"
	}, {
		"id" : "mark",
		"name" : "专题评论",
		"type" : "leaf",
		"icon" : "",
		"src" : "#"
	}, {
		"id" : "mark",
		"name" : "专题导购",
		"type" : "leaf",
		"icon" : "",
		"src" : "#"
	} ]
} ];