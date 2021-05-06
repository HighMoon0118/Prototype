# Prototype



### Material Calendar View

> 제가 개발하려는 앱의 주 기능은 일정관리이기 때문에 Calendar를 커스텀하는 것이 필수적입니다.
>
> https://github.com/prolificinteractive/material-calendarview
>
> https://github.com/Applandeo/Material-Calendar-View
>
> 위 두 링크는 많은 사람들이 커스텀하기위해 사용하는 라이브러리 깃헙 주소이고, 저는 두번째인 Applandeo의 Material-Calendar-View를 프로젝트에 사용하려고 합니다.
>
> 첫번째 라이브러리에서는 Calendar의 각 cell마다 drawable 만 지정할 수 있지만, 두번째 라이브러리는 TextView와 ImageView를 커스텀할 수 있기 때문에 좀 더 제 프로젝트에 적합하다고 판단했기 때문입니다.



1. 캘린더 뷰를 만들기 전에 뷰에 들어갈 Calendar 클래스를 만들어주고

   Room을 활용하기 위한 database, dao를 만들어주었다.

   Firebase를 통해 실시간으로 일정을 서로 공유하는 기능을 구현할 것이고

   Data, View Binding과 LiveData, ViewModel을 사용해보려한다.

