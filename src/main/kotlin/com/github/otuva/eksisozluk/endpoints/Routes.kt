package com.github.otuva.eksisozluk.endpoints

import com.github.otuva.eksisozluk.annotations.RequiresFormatting

//    "clientInfo" to "/v2/clientsettings/info",
//    "time" to "/v2/clientsettings/time",\
//     "userIndexTitlesBlock" to "/v2/user/indextitlesblock",
//    "userRemoveIndexTitlesBlock" to "/v2/user/indextitlesblock",

public object Routes {
    public const val api: String = "https://api.eksisozluk.com"

    public object Authentication {
        public const val userToken: String = "/Token"
        public const val anonToken: String = "/v2/account/anonymoustoken"
    }

    public object Entry {
        @RequiresFormatting
        public const val entry: String = "/v2/entry/%s"
        public const val vote: String = "/v2/entry/vote"
        public const val favorite: String = "/v2/entry/favorite"
        public const val unfavorite: String = "/v2/entry/unfavorite"

        @RequiresFormatting
        public const val favorites: String = "/v2/entry/%s/favorites"

        @RequiresFormatting
        public const val caylakFavorites: String = "/v2/entry/%s/caylakfavorites"
    }

    public object Topic {
        @RequiresFormatting
        public const val topic: String = "/v2/topic/%s/"

        @RequiresFormatting
        public const val snapshot: String = "/v2/topic/%s/tracked/%s"

        @RequiresFormatting
        public const val search: String = "/v2/topic/%s/search/%s"
        public const val advancedSearch: String = "/v2/topic/%s/search/advanced"

        @RequiresFormatting
        public const val query: String = "/v2/topic/query/?term=%s"

        public const val follow: String = "/v2/topic/follow"
        public const val unfollow: String = "/v2/topic/unfollow"
    }

    public object Index {
        public const val debe: String = "/v2/index/debe"
        public const val popular: String = "/v2/index/popular"
        public const val today: String = "/v2/index/today"
        public const val getChannelFilters: String = "/v2/index/getuserchannelfilters"
        public const val setChannelFilters: String = "/v2/index/setchannelfilter"
        public const val matters: String = "/v2/index/popularmatters"
        public const val mattersToday: String = "/v2/index/todaymatters"
        public const val caylak: String = "/v2/index/caylak"
        public const val last: String = "/v2/index/last"
        public const val olay: String = "/v2/index/olay"

        @RequiresFormatting
        public const val todayInPast: String = "/v2/index/todayinpast/%s"
    }

    public object User {
        @RequiresFormatting
        public const val user: String = "/v2/user/%s"
        public const val follow: String = "/v2/user/follow"
        public const val unfollow: String = "/v2/user/unfollow"
        public const val block: String = "/v2/user/block"
        public const val unblock: String = "/v2/user/unblock"
        public const val blockTopics: String = "/v2/user/indextitlesblock"
        public const val unblockTopics: String = "/v2/user/removeindextitlesblock"

        @RequiresFormatting
        public const val entries: String = "/v2/user/%s/entries"

        @RequiresFormatting
        public const val favorited: String = "/v2/user/%s/favorited"

        @RequiresFormatting
        public const val favorites: String = "/v2/user/%s/favorites"

        @RequiresFormatting
        public const val lastVoted: String = "/v2/user/%s/lastvoted"

        @RequiresFormatting
        public const val lastWeekMostVoted: String = "/v2/user/%s/lastweekmostvoted"

        @RequiresFormatting
        public const val selfFavorited: String = "/v2/user/%/selffavorited"

        @RequiresFormatting
        public const val bestEntries: String = "/v2/user/%s/bestentries"

        @RequiresFormatting
        public const val images: String = "/v2/user/%s/images"

        @RequiresFormatting
        public const val matters: String = "/v2/user/%s/matters"

        @RequiresFormatting
        public const val matterAnswers: String = "/v2/user/%s/matteranswers"
    }

    public object Search {
        public const val search: String = "/v2/index/search"

        @RequiresFormatting
        public const val autocomplete: String = "/v2/autocomplete/query/%s"
    }

    public object Message {
        public const val messages: String = "/v2/message"

        public const val archivedMessages: String = "/v2/message/archive"

        @RequiresFormatting
        public const val thread: String = "/v2/message/thread/Nick/%s"

        public const val send: String = "/v2/message/sendmessage"

//        public const val markAsRead: String = "/v2/message/markasread/nick"

//        public const val delete: String = "/v2/message/deleteprocessthread"

//        public const val archive: String = "/v2/message/archiveprocessthread"

//        public const val deleteArchive: String = "/v2/message/deleteprocessarchive"
    }

    public object Matter {
        @RequiresFormatting
        public const val matter: String = "/v2/matter/%s/"

        @RequiresFormatting
        public const val snapshot: String = "/v2/matter/%s/tracked/%s"
    }
}