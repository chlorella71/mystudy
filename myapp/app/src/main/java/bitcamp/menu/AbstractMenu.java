package bitcamp.menu;

import bitcamp.util.Stack;

public abstract class AbstractMenu implements Menu {


  protected Stack<String> breadcrumb;
  String title;

  public AbstractMenu(String title, Stack<String> breadcrumb) {
    this.title = title;
    this.breadcrumb = breadcrumb;
    //this.breadcrumb.push(title);
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  //public void setTitle(String title) {
  //  this.title = title;
  //}

  public String getMenuPath() {
    return String.join("/", breadcrumb.toArray(new String[0]));
  }
}