<template>
	<div id="app">
		<b-navbar toggleable="lg">
			<b-navbar-brand href="#">Academics</b-navbar-brand>
			<b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
			<b-collapse id="nav-collapse" is-nav>
				<b-navbar-nav>
					<li class="nav-item">
						<nuxt-link class="nav-link" to="students"
							>Students</nuxt-link
						>
					</li>
				</b-navbar-nav>
				<!-- Right aligned nav items -->
				<b-navbar-nav class="ml-auto">
					<b-nav-item-dropdown v-if="$auth.loggedIn" right>
						<!-- Using 'button-content' slot -->
						<template #button-content>
							<em>{{ $auth.user.sub }}</em>
						</template>
						<b-dropdown-item @click.prevent="signOut"
							>Sign Out</b-dropdown-item
						>
					</b-nav-item-dropdown>
					<li class="nav-item" v-else>
						<nuxt-link class="nav-link" to="/auth/login"
							>Sign In</nuxt-link
						>
					</li>
				</b-navbar-nav>
			</b-collapse>
		</b-navbar>
		<main>
			<Nuxt />
		</main>
	</div>
</template>

<script>
export default {
	methods: {
		signOut() {
			this.$auth.logout();
			this.$router.push("/");
		},
	},
};
</script>
