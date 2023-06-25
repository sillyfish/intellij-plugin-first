# [Actions](./Actions.kt)

## `InsertInlineCompletionAction`

该类定义了最基本的Action，继承于`EditorAction`。使用嵌套类`InsertInlineCompletionHandler`给父类提供构造函数的初始参数。

当处理写操作时，调用[Context](#Context)的[insert](#insert)方法。

## `InlineCompletionCaretListener`

该类实现`CaretListener`接口，用于监听光标位置变化时，触发[Context](#Context)重置。

## `InlineCompletionFocusListener`

该类实现`FocusChangeListener`接口，用于监听焦点丢失时，触发[Context](#Context)重置。

# [Context](./InlineCompletionContext.kt)

`InlineCompletionContext`，该类实现`Disposable`接口，控制Completion流程的上下文。

## 属性

### inlay

[Completion实现](#Completion实现)的实例，初始化时调用接口的工厂函数生成。

### isCurrentlyDisplayingInlays

判断当前Completion是否呈现。

## init

添加[光标监听](#InlineCompletionCaretListener)和[焦点监听](#InlineCompletionFocusListener)。

## 方法

### insert

### dispose

## 伴生对象

### 属性

#### INLINE_COMPLETION_CONTEXT_KEY

通过该Key存储[Context](#Context)对象。

### 扩展方法

#### Editor

##### getInlineCompletionContextOrNull

获取[Context](#Context)对象或null。

##### resetInlineCompletionContext

重置[Context](#Context)对象。

##### removeInlineCompletionContext

置[Context](#Context)对象为null。

# [Completion接口](./InlineCompletion.kt)

通过工厂函数（伴生对象实现）获取该接口的实现对象。

# [Completion实现](./EditorInlineInlineCompletion.kt)

[Completion接口](#Completion接口)的实现。