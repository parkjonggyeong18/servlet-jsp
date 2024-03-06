package com.nhnacademy.shoppingmall.common.mvc.view;
public class ViewResolver {

    public static final String DEFAULT_PREFIX="/WEB-INF/views/";
    public static final String DEFAULT_POSTFIX=".jsp";
    public static final String REDIRECT_PREFIX="redirect:";
    public static final String DEFAULT_SHOP_LAYOUT="/WEB-INF/views/layout/shop.jsp";
    public static final String DEFAULT_ADMIN_LAYOUT="/WEB-INF/views/layout/admin.jsp";
    public static final String LAYOUT_CONTENT_HOLDER = "layout_content_holder";

    private final String prefix;
    private final String postfix;

    public ViewResolver(){
        this(DEFAULT_PREFIX,DEFAULT_POSTFIX);
    }
    public ViewResolver(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    private void viewNameNullPointerException(String viewName){
        if(viewName == null){
            throw new NullPointerException("Parameter value is null");
        }
    }

    public  String getPath(String viewName){
        //todo#6-1  postfix+viewNAme+postfix 반환 합니다.
        viewNameNullPointerException(viewName);
        if(viewName.startsWith("/")){
            return this.prefix + viewName.substring(1) + this.postfix;
        }
        return this.prefix + viewName + postfix;
    }

    public boolean isRedirect(String viewName){
        //todo#6-2 REDIRECT_PREFIX가 포함되어 있는지 체크 합니다.
        viewNameNullPointerException(viewName);
        return viewName.toLowerCase().startsWith(REDIRECT_PREFIX);
    }

    public String getRedirectUrl(String viewName){
        //todo#6-3 REDIRECT_PREFIX를 제외한 url을 반환 합니다.
        viewNameNullPointerException(viewName);
        if(isRedirect(viewName)){
            return viewName.substring(REDIRECT_PREFIX.length());
        } else{
            throw new IllegalArgumentException("Redirect-URL이 아닙니다.");
        }
    }

    public String getLayOut(String viewName){

        /*
           /admin/경로가 포함되었다면 DEFAULT_ADMIN_LAYOUT 반환 합니다.
           /admin/경로가 포함되어 있지않다면 DEFAULT_SHOP_LAYOUT 반환 합니다.
        */
        viewNameNullPointerException(viewName);
        return viewName.contains("/admin/") ? DEFAULT_ADMIN_LAYOUT : DEFAULT_SHOP_LAYOUT;
    }
}
