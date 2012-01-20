package org.nutz.viv.module;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Modules(scanPackage=true)
@IocBy(args = {	"*org.nutz.ioc.loader.json.JsonLoader",
		"ioc",
		"*org.nutz.ioc.loader.annotation.AnnotationIocLoader",
		"org.nutz.viv"}, type = ComboIocProvider.class)
public class VivMainModule {}
